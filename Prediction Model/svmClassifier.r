library(e1071)
which(colnames(train)=="tmpf")#28
which(colnames(train)=="sknt")#32
which(colnames(train)=="alti")#34
which(colnames(train)=="gust")#37
which(colnames(train)=="skyl1")#42
which(colnames(train)=="skyl4")#45
svmModel <- svm(train[,c(28:30,32,34,36)], train$Delayed, type = "one-classification")
print(svmModel)
svmPred <- predict(svmModel, test[,c(28:30,32,34,36)])
eval(svmPred,test$Delayed)