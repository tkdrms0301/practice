import numpy as np
from sklearn import datasets
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import StandardScaler
from sklearn.svm import SVC
from sklearn.model_selection import cross_val_predict
from sklearn.metrics import classification_report

import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))

#page 31

iris = datasets.load_iris()
X = iris["data"][:, (2, 3)] # petal length, petal width
y = (iris["target"] == 2).astype(np.float64) # Iris-Virginica
from sklearn.model_selection import train_test_split
X_train, X_test = train_test_split(X, train_size = 0.8, random_state = 30)
y_train, y_test = train_test_split(y, train_size = 0.8, random_state = 30)

rbf_kernel_svm_clf = Pipeline((
("scaler", StandardScaler()),
#("svm_clf", SVC(kernel="rbf", gamma=5, C=0.001)),
#("svm_clf", SVC(kernel="rbf", gamma=0.1, C=0.001)),
#("svm_clf", SVC(kernel="rbf", gamma=0.1, C=1000)),
("svm_clf", SVC(kernel="rbf", gamma=5, C=1000)),
))
rbf_kernel_svm_clf.fit(X_train, y_train)

y_pred = cross_val_predict(rbf_kernel_svm_clf, X_test, y_test, cv=4)
print(classification_report(y_test, y_pred))