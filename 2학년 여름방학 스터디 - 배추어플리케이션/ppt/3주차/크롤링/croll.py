import math
import subprocess
import chromedriver_autoinstaller
from selenium import webdriver
import time
import json
from selenium.webdriver.chrome.options import Options
import openpyxl
import requests
from bs4 import BeautifulSoup


def delete_iframe(url):
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36"}
    res = requests.get(url, headers=headers)
    res.raise_for_status()  # 문제시 프로그램 종료
    soup = BeautifulSoup(res.text, "lxml")

    src_url = "https://blog.naver.com/" + soup.iframe["src"]

    return src_url


# 네이버블로그 본문 스크래핑
def text_scraping(url):
    headers = {"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36"}
    res = requests.get(url, headers=headers)
    res.raise_for_status() # 문제시 프로그램 종료
    soup = BeautifulSoup(res.text, "lxml")

    if soup.find("div", attrs={"class":"se-main-container"}):
        text = soup.find("div", attrs={"class":"se-main-container"}).get_text()
        text = text.replace("\n","") #공백 제거
        return text

    elif soup.find("div", attrs={"id":"postViewArea"}):
        text = soup.find("div", attrs={"id":"postViewArea"}).get_text()
        text = text.replace("\n","")
        return text
    else:
        return "네이버 블로그는 맞지만, 확인불가"


subprocess.Popen(
    r'C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\chrometemp"')  # 디버거 크롬 구동 # 크롬 실행 파일 경로

option = Options()
option.add_experimental_option("debuggerAddress", "127.0.0.1:9222")

chrome_ver = chromedriver_autoinstaller.get_chrome_version().split('.')[0]
try:
    driver = webdriver.Chrome(f'./{chrome_ver}/chromedriver.exe', options=option)
except:
    chromedriver_autoinstaller.install(True)
    driver = webdriver.Chrome(f'./{chrome_ver}/chromedriver.exe', options=option)
driver.implicitly_wait(10)
# -------------------------- 드라이버 로드 --------------------------
foods_data = dict()

xlFile_path = "C:\\Users\\ATIV\\Desktop\\fl.xlsx" # 음식 리스트 파일 경로
xlFile = openpyxl.load_workbook(xlFile_path, data_only=True)
ws = xlFile['Sheet1']['A']
food_list = []
food_count = dict()
for food in ws:
    food_list.append(food.value)
file_path = "./foods.json"
# -------------------------- 변수 --------------------------
for food in food_list:
    url_list = []  # 블로그 url을 저장하기 위한 변수
    post_list = []  # 블로그 post를 누적하기 위한 변수
    url = 'https://section.blog.naver.com/Search/Post.nhn?pageNo=1' + '&rangeType=ALL&orderBy=sim&keyword=배달 ' + food  # url 값 설정
    driver.get(url)
    post_count = driver.find_element_by_css_selector('#content > section > div.category_search > div.search_information > span > span > em').text
    post_count = post_count.replace(",", "")
    post_count = int(post_count.replace("건", ""))
    CRAWLING_RATE = 0.1
    CRAWLING_MIN = 500
    CRAWLING_MAX = 2000
    page = 0
    if((post_count * CRAWLING_RATE) < CRAWLING_MIN):
        page = math.ceil(CRAWLING_MIN / 7)
    elif((post_count * CRAWLING_RATE) > CRAWLING_MAX):
        page = math.ceil(CRAWLING_MAX / 7)
    else:
        page = math.ceil(post_count * CRAWLING_RATE / 7)

    for i in range(1, page+1):  # 페이지 범위 지정 (시작, 끝+1)
        url = 'https://section.blog.naver.com/Search/Post.nhn?pageNo=' + str(i) + '&rangeType=ALL&orderBy=sim&keyword=배달 ' + food  # url 값 설정
        driver.get(url)
        time.sleep(0.5)  # 오류 방지 sleep

        for j in range(1, 8):
            titles = driver.find_element_by_xpath('/html/body/ui-view/div/main/div/div/section/div[2]/div[' + str(j) + ']/div/div[1]/div[1]/a[1]')
            title = titles.get_attribute('href')
            if('222421891481' not in title):
                url_list.append(title)
    # ----- URL 수집 -----

    for url in url_list:  # 저장했던 블로그 하나씩 순회
        post = text_scraping(delete_iframe(url))
        post_list.append(post)
    foods_data[food] = post_list
    with open(file_path, 'w+', encoding='UTF-8') as f:
        json.dump(foods_data, f, ensure_ascii=False, indent='\t')
    print(food + "저장완료!")
