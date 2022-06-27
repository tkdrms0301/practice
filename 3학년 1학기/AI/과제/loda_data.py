import pandas as pd
import sample_data
import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))

sample_data.create_learning_data("sample1")
my_data = pd.read_csv("sample1")

my_data.info()
heads = my_data.head()
print(heads)

tails = my_data.tail()
print(tails)

desc = my_data.describe()
print(desc)