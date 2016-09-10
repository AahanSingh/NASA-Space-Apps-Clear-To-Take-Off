#predict mslp , vsby , gust , skyl1, skyl2, skyl3, skyl4, drct
options(digits = 10)

#mslp
x <- final[final$mslp!="M",c(1,2,35)]
x$mslp <- as.numeric(as.character(x$mslp))
l <- lm(mslp ~ Date + CRSDepTime, data = x)
t <- final[final$mslp == "M", c(1,2)]
p <- predict(l, t)
final$mslp <- as.numeric(as.character(final$mslp))
final$mslp[is.na(final$mslp)] <- p
#vsby
v <- final[final$vsby!="M",c(1,2,which(colnames(final)=="vsby"))]
v$vsby <- as.numeric(v$vsby)
l <- lm(vsby ~ Date + CRSDepTime, data=v)
t <- final[final$vsby == "M", c(1,2)]
p <- predict(l,t)
final$vsby <- as.numeric(final$vsby)
final$vsby[is.na(final$vsby)] <- p
#gust
v <- final[final$gust!="M",c(1,2,which(colnames(final)=="gust"))]
v$gust <- as.numeric(as.character(v$gust))
l <- lm(gust ~ Date + CRSDepTime, data=v)
t <- final[final$gust == "M", c(1,2)]
p <- predict(l,t)
final$gust <- as.numeric(as.character(final$gust))
final$gust[is.na(final$gust)] <- p
#skyl1
v <- final[final$skyl1!="M",c(1,2,which(colnames(final)=="skyl1"))]
v$skyl1<-as.numeric(as.character(v$skyl1))
l <- lm(skyl1 ~ Date + CRSDepTime, data=v)
t <- final[final$skyl1 == "M", c(1,2)]
p <- predict(l,t)
final$skyl1 <- as.numeric(as.character(final$skyl1))
final$skyl1[is.na(final$skyl1)]<-p
#skyl2
v <- final[final$skyl2!="M",c(1,2,which(colnames(final)=="skyl2"))]
v$skyl2<-as.numeric(as.character(v$skyl2))
l <- lm(skyl2 ~ Date + CRSDepTime, data=v)
t <- final[final$skyl2 == "M", c(1,2)]
p <- predict(l,t)
final$skyl2 <- as.numeric(as.character(final$skyl2))
final$skyl2[is.na(final$skyl2)]<-p
#skyl3
v <- final[final$skyl3!="M",c(1,2,which(colnames(final)=="skyl3"))]
v$skyl3<-as.numeric(as.character(v$skyl3))
l <- lm(skyl3 ~ Date + CRSDepTime, data=v)
t <- final[final$skyl3 == "M", c(1,2)]
p <- predict(l,t)
final$skyl3 <- as.numeric(as.character(final$skyl3))
final$skyl3[is.na(final$skyl3)]<-p
#skyl4
v <- final[final$skyl4!="M",c(1,2,which(colnames(final)=="skyl4"))]
v$skyl4<-as.numeric(as.character(v$skyl4))
l <- lm(skyl4 ~ Date + CRSDepTime, data=v)
t <- final[final$skyl4 == "M", c(1,2)]
p <- predict(l,t)
final$skyl4 <- as.numeric(as.character(final$skyl4))
final$skyl4[is.na(final$skyl4)]<-p
#drct
v <- final[final$drct!="M",c(1,2,which(colnames(final)=="drct"))]
v$drct<-as.numeric(as.character(v$drct))
l <- lm(drct ~ Date + CRSDepTime, data=v)
t <- final[final$drct == "M", c(1,2)]
p <- predict(l,t)
final$drct <- as.numeric(as.character(final$drct))
final$drct[is.na(final$drct)]<-p


final$tmpf <- as.numeric(final$tmpf)
final$dwpf <- as.numeric(final$dwpf)
final$relh <- as.numeric(final$relh)
final$sknt <- as.numeric(final$sknt)
final$alti <- as.numeric(final$alti)

