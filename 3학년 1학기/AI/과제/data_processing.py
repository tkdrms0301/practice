import pandas as pd
import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))

my_data = pd.read_csv("iris/iris.csv")
my_data_label = my_data['class'].copy()
my_data_inputs = my_data.drop('class', axis = 1)

print(my_data_inputs)

from sklearn.model_selection import train_test_split
train_set, test_set = train_test_split(my_data, train_size = 0.8, random_state = 30)

print(train_set)
print(test_set)
