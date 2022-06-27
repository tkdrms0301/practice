import numpy
import matplotlib.pyplot as plt
import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))

arr = [10, 386, 479, 627, 20, 523, 482, 483, 542, 699, 535, 617, 577, 471, 615, 583, 441, 562, 563, 527, 453, 530, 433, 541, 585, 704, 443, 569, 430, 637, 331, 511, 552, 496, 484, 566, 554, 472, 335, 440, 579, 341, 545, 615, 548, 604, 439, 556, 442, 461, 624, 611, 444, 578, 405, 487, 490, 496, 398, 512, 422, 455, 449, 432, 607, 679, 434, 597, 639, 565, 415, 486, 668, 414, 665, 763, 557, 304, 404, 454, 689, 610, 483, 441, 657, 590, 492, 476, 437, 483, 529, 363, 711, 543]
elements = numpy.array(arr)
mean = numpy.mean(elements, axis=0)
sd = numpy.std(elements, axis=0)

data_cleaning_list = list()
final_list = list()
for x in arr:
    if (x > mean - 2 * sd) and (x < mean + 2 * sd):
        final_list.append(x)
    else:
        data_cleaning_list.append(x)
print("data_cleaning_list"); print(data_cleaning_list)
print("final_list"); print(final_list)

