from sklearn.datasets import load_iris
from sklearn.metrics import classification_report
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier

import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))

iris = load_iris()
print(iris.feature_names)
X = iris.data[:, 2:] # petal length and width
y = iris.target
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.33, shuffle=True, random_state=0)
tree_clf = DecisionTreeClassifier(max_depth=2)
tree_clf.fit(X_train, y_train)

y_pred = tree_clf.predict(X_test)

print(classification_report(y_test, y_pred))