colSums(is.na(atl))>0
which(colnames(atl) == "ArrDelay")
comp <- complete.cases(atl[,12])
sum(comp)
sum(!is.na(atl$ArrDelay))
atl <- atl[comp,]
nrow(atl)
#removed NA values for arrdelay
summary(atl$ArrDelay)
sum(is.na(atl$ArrDelay))
colSums(is.na(atl)) > 0
comp <- complete.cases(atl[,c(23,26)])
nrow(atl[comp,])
nrow(atl[is.na(atl$LateAircraftDelay) & is.na(atl$WeatherDelay),])
nrow(atl) - nrow(atl[is.na(atl$LateAircraftDelay) & is.na(atl$WeatherDelay),])
atl <- atl[comp,]
nrow(atl)
colSums(is.na(atl)) > 0
is.na(atl)
sum(is.na(atl))
nrow(atl) / nrow(ATL) *100
#removed all na values
is.na(weather)
sum(is.na(weather))
