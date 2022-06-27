import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))
a = "abc"; b = "abc"; c = "a"; d = 'a'; e = "ab"
z = a is b ; print(z) # true
z = c is d ; print(z) # true
z = a[0] is c ; print(z) # true
z = a[0:2] is e; print(z)# false

list1 = [1, 2, [3, 4]]; list2 = [3, 4]; list3 = [3, 4]
z = list2 is list3 ; print(z) # false
z = list1[2] is list2 ; print(z) # false