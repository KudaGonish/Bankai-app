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
Попробовать launchedEffect(key1 = true)


https://www.dns-shop.ru/product/8df945c77608ed20/pk-zet-gaming-neo-m091/characteristics/

https://www.dns-shop.ru/product/ab5d591603fded20/156-noutbuk-asus-expertbook-p1-p1512cea-bq0390-seryj/characteristics/

https://www.dns-shop.ru/product/25e0ec9563cded20/156-noutbuk-infinix-inbook-x2-plus-xl25-seryj/

https://www.dns-shop.ru/product/f20aacf55e2bed20/156-noutbuk-asus-tuf-gaming-f15-fx506lh-hn042-cernyj/

https://kemerovo.e2e4online.ru/catalog/item/sistemnyy-blok-e2e4-engineer-enr-i11400-16-480-h-1011086/


https://kemerovo.e2e4online.ru/catalog/item/sistemnyy-blok-mastero-middle-mc09-mc09-i10700-1-1138560/

https://kemerovo.e2e4online.ru/catalog/item/sistemnyy-blok-mastero-middle-mc04-mc04-i10100-1-1138549/

https://kemerovo.e2e4online.ru/catalog/item/sistemnyy-blok-e2e4-designer-dgn-i10400-16-240-h-908800/

https://kemerovo.e2e4online.ru/catalog/item/noutbuk-tecno-megabook-t1-tcn-t1i5w16-512-gr-1150276/

https://kemerovo.e2e4online.ru/catalog/item/noutbuk-lenovo-v15-v15-g2-itl-g2-82kb011hak-16g-1150158/

Bbh
