library(randomForest)
RndmFrstModel <- randomForest(as.factor(Delayed) ~ tmpf + dwpf + relh + sknt + alti + vsby, data = train , ntree = 1000, importance = TRUE)
RndmFrstPred <- predict(RndmFrstModel, test)
eval(RndmFrstPred,test$Delayed)