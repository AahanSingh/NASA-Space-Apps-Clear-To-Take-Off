library(caret)
library(nnet)
library(rpart)
library(rattle)
library(rpart.plot)
library(RColorBrewer)
library(e1071)
final$Delayed[final$DepTime > final$CRSDepTime] <- TRUE
final$Delayed[final$DepTime <= final$CRSDepTime] <- FALSE
final$ANN <- class.ind(final$Delayed)
t <- createDataPartition(final$Delayed, p = 0.8, list = FALSE)
train <- final[t,]
test <- final[-t,]
rm(t)