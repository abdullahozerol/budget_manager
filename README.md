<img src="icon.png" align="right" />

# Budget Manager Mobil Uygulaması
> GDG Turkey Android Bootcamp Bitirme Projesi

Harcamalarınızı takip edebileceğiniz, kategorilendirebileceğiniz ve güncel olarak kur karşılığını bulabileceğiniz, bunları aynı zamanda internet bağlantınız olmadan da yapmanıza imkan sağlayan bir harcama takip uygulaması. 
  
## Kullanılan Teknolojiler

 - Kotlin
 - MVVM
 - Navigation
 - Data Binding
 - Room Database
 - Recycler View
 - Retrofit
 - Fixer Api

## Başlangıç Ekranı
> Splash Screen

![budget_manager_splash_screen](https://user-images.githubusercontent.com/82768343/117546013-f75e4000-b030-11eb-8500-5a213e073795.gif)

Uygulamaya ilk girişte gösterilen başlangıç ekranı.

## Karşılama ekranı
> Onboarding Screen

![budget_manager_onboarding_screen](https://user-images.githubusercontent.com/82768343/117546104-705d9780-b031-11eb-9dc5-ccbc45aed10f.gif)

Uygulama ilk açıldığı zaman başlangıç ekranından sonra bir defa gözükmekte olan uygulama tanıtım ekranı. Kullanıcı uygulamaya tekrar girmesi durumunda bu ekran gözükmemektedir.

## Profil Ekranı

![budget_manager_profile_screen](https://user-images.githubusercontent.com/82768343/117545794-ff69b000-b02f-11eb-9503-a60741136330.gif)

Kullanıcının ismini girip cinsiyet tercihi yapması durumunda ana ekranda bir karşılama metni gözükmektedir.

## Harcama Ekleme Ekranı

![budget_manager_add_expense](https://user-images.githubusercontent.com/82768343/117546500-689ef280-b033-11eb-8387-cf7e564660be.gif)

Kullanıcıların harcamalarını kategorileyerek, seçeceği kura göre ekleyebileceği harcama ekleme sayfası.

## Eksik Bilgi Girme Kontrolü

![budget_manager_input_control](https://user-images.githubusercontent.com/82768343/117547460-65f2cc00-b038-11eb-9b3d-15382f595f0d.gif)

Kullanıcıların ekleme işlemi yaparken eksik bilgi olup olmadığının kontrol edilmesi.

## Ana Ekran Kur Seçimi

![budget_manager_home_change_currency](https://user-images.githubusercontent.com/82768343/117546727-815bd800-b034-11eb-8ae2-11569a83d77c.gif)

Girilen harcamaların seçilen kura göre güncel olarak listelenmesi ve aynı zamanda toplam harcama tutarı gösterilmesi. 

## Harcama Detay Ekranı ve Silme İşlemi

![budget_manager_detail_screen](https://user-images.githubusercontent.com/82768343/117546922-82413980-b035-11eb-9f75-2fb486cedef9.gif)

Kullanıcıların ekledikleri harcamaları kategorisiyle ve eklediği kur ile birlikte görebileceği detay sayfası. Aynı zamanda kullanıcılar silmek istediği harcamayı bu sayfaya girerek silebilmektedir.

## İnternet Bağlantısı Olmadan Çalışabilme

![budget_manager_offline_mode](https://user-images.githubusercontent.com/82768343/117547134-a8b3a480-b036-11eb-88d4-b17cb5ebaf4d.gif)

Kullanıcılar internet bağlantısı olmaksızın, internet bağlantısının en son var olduğu kura göre işlemlerini yapabilmektedir.

## Api Url Bilgilendirme

Kullanılan Api Url ile ilgili bilgilere [buradan](Api.md) ulaşabilirsiniz.
