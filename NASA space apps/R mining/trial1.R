  flight <- read.table("XFLIGHT.csv", header = TRUE , sep = ",")
  airport <- read.table("XAIRPORT.csv", header = TRUE , sep = ",")
  flight$ORIGIN <- as.character(flight$ORIGIN)
  flight$DEST <- as.character(flight$DEST)
  airport$airport <- as.character(airport$airport)
  

  d1 <- NULL
  d1 <- data.frame()
  d1 <- matrix(d1,ncol = 4)
  
  d2 <- NULL
  d2 <- data.frame()
  d2 <- matrix(d2,ncol = 4)
  
  
  for ( day in 9:16) {
    for( hour in 0:23){
      
      for( arpt in 1:nrow(airport)){
          sumdep = 0 
          countdep = 0
          
          sumarr = 0
          countarr = 0
          flagd = 0
          flaga = 0
          for( i in 1:nrow(flight)){
            if(flight$DATE[i] == day){
              if(flight$DEP[i] == hour){
                 if(flight$ORIGIN[i] == airport$airport[arpt]){
                    sumdep = sumdep + flight$DEP_DELAY_GROUP[i]
                    countdep = countdep + 1
                    flagd = 1                
                 }
              }
              
              if(flight$ARR[i] == hour){
                if(flight$DEST[i] == airport$airport[arpt]){
                  sumarr = sumarr + flight$ARR_DELAY_GROUP[i]
                  countarr = countarr + 1
                  flaga = 1                
                }
              }
              
            }
          }
          if(flagd == 1)
          {
            sumdep = sumdep/countdep
            d1 <- rbind(d1,c(airport$airport[arpt],day,hour,sumdep))
          }
          if(flaga == 1)
          {
            sumarr = sumarr/countarr
            d2 <- rbind(d2,c(airport$airport[arpt],day,hour,sumarr))
          }
          
      }    
      
    }
  }
  
  
  
  
  
  
  
  
  