from sklearn.datasets import load_iris
import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))

#load dataset
iris = load_iris()
X = iris['data']
y = iris['target']

#train_dataset, test_dataset
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, train_size = 0.8, random_state = 30)

#load model
from sklearn.naive_bayes import GaussianNB
gnb_clf = GaussianNB()
gnb_clf.fit(X_train,y_train)

#performance evaluation
from sklearn.model_selection import cross_val_predict
from sklearn.metrics import classification_report
y_pred = cross_val_predict(gnb_clf, X_test, y_test, cv=3)
print(classification_report(y_test, y_pred))
