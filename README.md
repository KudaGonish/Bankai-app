# Bankai-app
```
navController.previousBackStackEntry
    ?.savedStateHandle
    ?.set("your_key", "your_value")
navController.popBackStack()
and then, from the source Composable, you can listen for changes using a LiveData.

val secondScreenResult = navController.currentBackStackEntry
    ?.savedStateHandle
    ?.getLiveData<String>("your_key")?.observeAsState()
...
secondScreenResult?.value?.let {
    // Read the result
}
```




If you need only once get value, you need remove value after usage:

```
val screenResultState = navController.currentBackStackEntry
    ?.savedStateHandle
    ?.getLiveData<String>("some_key")?.observeAsState()

screenResultState?.value?.let {
    ...
    // make something, for example `viewModel.onResult(it)`
    ...
    //removing used value
    navController.currentBackStackEntry
        ?.savedStateHandle
        ?.remove<String>("some_key")
}
```
I also extract it in function (for JetPack Compose)
```
@Composable
fun <T> NavController.GetOnceResult(keyResult: String, onResult: (T) -> Unit){
    val valueScreenResult =  currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(keyResult)?.observeAsState()

    valueScreenResult?.value?.let {
        onResult(it)
       
        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(keyResult)
    }
}
```
you can copy it to your project and use like this:
```
navController.GetOnceResult<String>("some_key"){
    ...
    // make something
}
```


CANVAS
10%
```
drawArc(
            Color.Blue,
            startAngle = 70f,
            sweepAngle = 40f,
            useCenter = true,
            size = Size(250.dp.toPx(), 250.dp.toPx())
        )
 ```
 50%
```
startAngle = 0f,
sweepAngle = 180f,
```

25%
```
startAngle = 45f,
sweepAngle = 90f,
            ```
            
            
            https://www.youtube.com/watch?v=kcpJmDM2jwU
            https://reddeveloper.ru/questions/jetpack-compose-kak-initsiirovat-sobytiye-pri-sostavlenii-ekrana-195xa
