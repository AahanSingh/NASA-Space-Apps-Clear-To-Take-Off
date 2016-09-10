findBusiestFlight <- function(x)
{
  s <- sum(atl2005$UniqueCarrier == x)
  data.frame(Origin = x , Count = s)
  cat(x,":",s,"\n")
}