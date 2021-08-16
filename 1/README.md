1. Show a list of Currency Wallets (Icon, Symbol, balance). 
   As you can see there is a lot of code reuse, try to improve it 
   Cryptocoin and Metal are considered as Asset, Fiat and Asset are considered as Currency. 
   Each Currency has a Wallet, and you can have multiple Wallets per Currency.

  a. for Metals show the name in the list eg.: "Gold" 
  b. do not show deleted wallets
  c. Data should only be retrieved from the Repository

2. Sort the list by type : fiat, cryptocoins, metals and the balance of the wallet
3. Add a functionality to filter Currencies by type (a simple button which rotates the type is enough)
4. If you click on an entry open a simple DetailView where you show the price of the coin
   a. use precision to format the price with correct amount of decimal places
   b. Prices are euro prices

5. Test your implementation with Unit Tests


GENERAL

* You are free to refactor and improve the given structure. let us know what and why
* Use an architecture pattern of your choice

NICE TO HAVE 
* You are free to implement also a nice UI 

-----------------------------------------------------------------------------------------

1. The data was modified to avoid the code repeated and fixed the structure.
Currently any Wallet have a Currency and this currency is a Fiat or an Asset, and the assets could be Cryptocoin or Metal.
A recyclerview and adapter has been created to show the list of Currency Wallets. The adapter filter the deleted wallets and show the name for Metals currency wallets. All the data is retrieved from the repository.

<img src="https://github.com/E7-Company/Bitpanda-Android-Developer-Test/blob/master/1/Currency%20Wallets.jpg" width="340" height="285">

2. The data is sorted by type and balance at the repository previous to show in the list. 

3. A floating action button is used to change or filter the Currencies by type, it changes the items from the list and the title name.

4. A new view was created to show the price with the correct precision and the € symbol. The price is sent like extra by intent to avoid to access to the repository again.

5. I did Unit and UI tests for all tasks.


Using Dagger Hilt, Lifecycle, Binding, Navigation, Compose and last Kotlin version.

