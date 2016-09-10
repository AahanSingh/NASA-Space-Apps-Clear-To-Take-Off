library(nnet)
ANNModel <- nnet(formula = ANN ~  tmpf + dwpf + relh + sknt + alti + vsby , data = train, size = 8, softmax = TRUE)
ANNPred <- predict(ANNModel, test)
ANNPred <- ANNPred[,2] > ANNPred[,1]
eval(ANNPred,test$Delayed)