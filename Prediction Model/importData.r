 
 A5 <- read.csv("2005.csv")
 A6 <- read.csv("2006.csv")
 A7 <- read.csv("2007.csv")
 A8 <- read.csv("2008.csv")
 
 A <- rbind.data.frame(A5, A6, A7, A8)
 
 rm(A5)
 rm(A6)
 rm(A7)
 rm(A8)
 