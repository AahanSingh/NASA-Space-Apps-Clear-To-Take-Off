findBusiest <- function(x)
{
  s <- sum(A$Origin == x)
  data.frame(Origin = x , Count = s)
}