import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))
testStr = "i love you"
z = 'i' in testStr; print(z)
z = "love" in testStr; print(z)
z = "Love" in testStr; print(z)
testTuple = (1, 2, (3, 4))
z = 1 in testTuple; print(z)
z = (1, 2) in testTuple; print(z)
z = (3, 4) in testTuple; print(z)
testList = [[1, 2], 3, 4]
z = 1 in testList; print(z)
z = [1, 2] in testList; print(z)
z = [3, 4] in testList; print(z)