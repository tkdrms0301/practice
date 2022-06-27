import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))

def swap(x, y):
    tmp = x
    x = y
    y = tmp
    return x, y

data1 = 7
data2 = 5
data1, data2 = swap(data1, data2)
print(data1, data2)