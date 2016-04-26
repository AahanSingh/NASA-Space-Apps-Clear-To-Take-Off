options(warn=-1)
library("e1071")

args <- commandArgs(trailingOnly = TRUE)

t <- args[1]
d <- args[2]
h <- args[3]
s <- args[4]
a <- args[5]

mydata <- read.table("final2.csv",header=TRUE,sep = ",")
#input <- read.table("check1.csv",header=TRUE,sep = ",")
#test comment

mydata$X <- NULL


mydata$tmpf <- as.factor(mydata$tmpf)
mydata$dwpf <- as.factor(mydata$dwpf)
mydata$relh <- as.factor(mydata$relh)
mydata$sknt <- as.factor(mydata$sknt)
mydata$alti <- as.factor(mydata$alti)

input <- data.frame()
input <- matrix(input,ncol=22)
colnames(input) = colnames(mydata)
input <- rbind(input,head(mydata,1))

 input$tmpf <-t
 input$dwpf <-d
 input$relh <-h
 input$sknt <-s


model0 <- naiveBayes(del0 ~ tmpf+dwpf+relh+sknt,mydata)
model1 <- naiveBayes(del1 ~ tmpf+dwpf+relh+sknt,mydata)
model2 <- naiveBayes(del2 ~ tmpf+dwpf+relh+sknt,mydata)
model3 <- naiveBayes(del3 ~ tmpf+dwpf+relh+sknt,mydata)
model4 <- naiveBayes(del4 ~ tmpf+dwpf+relh+sknt,mydata)
model5 <- naiveBayes(del5 ~ tmpf+dwpf+relh+sknt,mydata)
model6 <- naiveBayes(del6 ~ tmpf+dwpf+relh+sknt,mydata)
model7 <- naiveBayes(del7 ~ tmpf+dwpf+relh+sknt,mydata)
model8 <- naiveBayes(del8 ~ tmpf+dwpf+relh+sknt,mydata)
model9 <- naiveBayes(del9 ~ tmpf+dwpf+relh+sknt,mydata)
model10 <- naiveBayes(del10 ~ tmpf+dwpf+relh+sknt,mydata)
model11 <- naiveBayes(del11 ~ tmpf+dwpf+relh+sknt,mydata)
model12 <- naiveBayes(del12 ~ tmpf+dwpf+relh+sknt,mydata)




result0 <- predict (model0,input)
result1 <- predict (model1,input)
result2 <- predict (model2,input)
result3 <- predict (model3,input)
result4 <- predict (model4,input)
result5 <- predict (model5,input)
result6 <- predict (model6,input)
result7 <- predict (model7,input)
result8 <- predict (model8,input)
result9 <- predict (model9,input)
result10 <- predict (model10,input)
result11 <- predict (model11,input)
result12 <- predict (model12,input)


if( result12 == "Yes"){
  print("12")
}else if( result11 == "Yes"){
  print("11")
}else if( result10 == "Yes"){
  print("10")
}else if( result9 == "Yes"){
  print("9")
}else if( result8 == "Yes"){
  print("8")
}else if( result7 == "Yes"){
  print("7")
}else if( result6 == "Yes"){
  print("6")
}else if( result5 == "Yes"){
  print("5")
}else if( result4 == "Yes"){
  print("4")
}else if( result3 == "Yes"){
  print("3")
}else if( result2 == "Yes"){
  print("2")
}else if( result1 == "Yes"){
  print("1")
}else{
  print("0")
}
