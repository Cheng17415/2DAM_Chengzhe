fun main(){
    for (i in 1..10){
        for(j in 1..10){
            print(String.format("%4d",i*j))
        }
        println()
    }
    for(i in 1..10 step 2)
        println(i)
    for(i in 10 downTo 1)
        println(i)
    for(i in 10 downTo 1 step 2)
        println(i)
    for(x in 0 until 10)
        println(x)
}