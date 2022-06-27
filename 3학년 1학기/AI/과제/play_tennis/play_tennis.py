import pandas as pd
from sklearn.model_selection import cross_val_score
import time
print("20180661 안상근 " + time.strftime('%c', time.localtime(time.time())))

#load play_tennis dataset
df=pd.read_csv("play_tennis.csv")

#use labelencoder
from sklearn import preprocessing
string_to_int= preprocessing.LabelEncoder() #encode your data
df=df.apply(string_to_int.fit_transform) #fit and transform it
print(df); print()

#data, result
features = ["outlook", "temp", "humidity", "wind"]
target = ["play"]
X = df[features]
y = df[target]

#model load
from sklearn.naive_bayes  import GaussianNB
gnb_clf = GaussianNB()
gnb_clf.fit(X,y.values.ravel())

#cross validation
accuracy = cross_val_score(gnb_clf, X, y.values.ravel(), cv=5, scoring="accuracy")
print("accuracy : ", accuracy)