import numpy as np
import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))

def create_learning_data(file_name):
	f = open(file_name, "w")
	f.write("x,y,z\n")

	np.random.seed(30)
	x = 50 * np.random.rand(100)
	y = 50 * np.random.rand(100)
	z = 3*x + 2*y + 4 + np.random.randn(100)

	for v1, v2, v3 in zip(x, y, z):
		f.write(str(v1) + "," + str(v2) + "," + str(v3) + "\n")
	f.close()