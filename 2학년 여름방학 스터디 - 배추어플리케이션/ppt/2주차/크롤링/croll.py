import subprocess
import chromedriver_autoinstaller
from selenium import webdriver
import time
import json
from selenium.webdriver.chrome.options import Options
import openpyxl

subprocess.Popen(
    r'C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\chrometemp"')  # 크롬 실행 파일 경로 입력 \\

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

xlFile_path = "C:\\Users\\ATIV\\Desktop\\food.xlsx" # xlsx 액셀 파일 경로 입력 \\
xlFile = openpyxl.load_workbook(xlFile_path, data_only=True)
ws = xlFile['Sheet1']['A']
food_list = []
for food in ws:
    food_list.append(food.value)
file_path = "./foods.json"
# -------------------------- 변수 --------------------------
for food in food_list:
    url_list = []  # 블로그 url을 저장하기 위한 변수
    post_list = []  # 블로그 post를 누적하기 위한 변수
    for i in range(1, 21):  # 페이지 범위 지정 (시작, 끝+1)
        url = 'https://section.blog.naver.com/Search/Post.nhn?pageNo=' + str(
            i) + '&rangeType=ALL&orderBy=sim&keyword=배달' + food  # url 값 설정
        driver.get(url)
        time.sleep(0.5)  # 오류 방지 sleep

        for j in range(1, 8):
            titles = driver.find_element_by_xpath('/html/body/ui-view/div/main/div/div/section/div[2]/div[' + str(j) + ']/div/div[1]/div[1]/a[1]')
            title = titles.get_attribute('href')
            url_list.append(title)
    # ----- URL 수집 -----

    for url in url_list:  # 저장했던 블로그 하나씩 순회
        driver.get(url)

        driver.switch_to.frame('mainFrame')
        overlays = ".se-component.se-text.se-l-default"  # 내용 크롤링
        texts = driver.find_elements_by_css_selector(overlays)

        post = ""  # post 1개 내용을 담을 변수
        for txt in texts:
            post = post + txt.text  # 각 블로그의 내용을 변수에 누적함

        post_list.append(post)
    foods_data[food] = post_list
with open(file_path, 'w+', encoding='UTF-8') as f:
    json.dump(foods_data, f, ensure_ascii=False, indent='\t')
