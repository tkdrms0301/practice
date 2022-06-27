import os
from six.moves import urllib
import tarfile
import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))
def download_data(url, dir_name, file_name):
	if not os.path.isdir(dir_name):
		os.makedirs(dir_name)
	file_path = os.path.join(dir_name,file_name)
	urllib.request.urlretrieve(url, file_path)

def extract_all_from_tarfile(file_name): #tar 파일을 저장한 후에 압축을 해제
	tar_file = tarfile.open(file_name)
	tar_file.extractall(path=os.path.splitext(file_name)[0])
	tar_file.close()

dir_name = "datasets/iris"
file_name = "iris.csv"
download_data("https://www.openml.org/data/get_csv/61/dataset_61_iris.arff", dir_name, file_name )
