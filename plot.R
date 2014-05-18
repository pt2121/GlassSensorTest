dat = read.csv("accelerometer_data140517_203942.csv", header = TRUE)

#ggplot(dat, aes(x = timestamp, y = value, color = variable)) + geom_line(aes(y = y, col = "y"))   + geom_line(aes(y = ly, col = "ly"))   + geom_line(aes(y = x, col = "x"))   + geom_line(aes(y = lx, col = "lx"))   + geom_line(aes(y = z, col = "z"))   + geom_line(aes(y = lz, col = "lz"))
 
ggplot(dat, aes(x = timestamp, y = value , color = variable)) + geom_line(aes(y = ly, col = "ly")) + geom_line(aes(y = y, col = "y"))