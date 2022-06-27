import pandas as pd
import sample_data
import matplotlib.pyplot as plt
import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))

sample_data.create_learning_data("sample1")
my_data = pd.read_csv("sample1")

my_data.hist(bins=50, layout=(1,3), figsize=(10,3))
plt.show()

my_data.hist(bins=50, column="z")
plt.show()

my_data.plot(kind="scatter", x ='x', y='z')
plt.show()
