#ATL data is x compare with weather date and time and merge
#library(foreach)
#library(doMC)
#registerDoMC(3)  #change the 2 to your number of CPU cores  
mergeWeather <- function()
{
  foreach(i = 1:100) %dopar% {
    foreach(j = 1:100) %dopar% {
      if(cpy$Date[i] == weather$Date[j]){
        if(cpy$CRSDepTime[i]==weather$Time[j]){
          cat(cpy$CRSDepTime,weather$Time,"\n")
          cpy$tmpf[i] = weather$tmpf[j]
         }
      }
    }
  }
}