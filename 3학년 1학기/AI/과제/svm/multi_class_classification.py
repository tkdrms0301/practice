from sklearn.datasets import load_iris
from sklearn.metrics import classification_report
from sklearn.model_selection import train_test_split, cross_val_predict
from sklearn.multiclass import OneVsOneClassifier, OneVsRestClassifier
from sklearn.svm import LinearSVC

import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))

X, y = load_iris(return_X_y=True)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.33, shuffle=True, random_state=0)
clf = OneVsOneClassifier(LinearSVC(random_state=0)).fit(X_train,y_train)
#clf = OneVsRestClassifier(LinearSVC(random_state=0)).fit(X_train,y_train)
predicted = clf.predict(X_test[:10])
y_pred = cross_val_predict(clf, X_test, y_test, cv=4)
print(predicted)
print('\n')
print(y_pred)
print(classification_report(y_test, y_pred))