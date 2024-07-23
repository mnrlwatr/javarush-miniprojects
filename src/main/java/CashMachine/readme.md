taskKey="CashMachine.big15"\n\nCashMachine (15)

1. В CashMachine создай константу - путь к ресурсам.
public static final String RESOURCE_PATH;
Отрефакторь загрузку всех ResourceBundle с учетом RESOURCE_PATH.

2. В классе CashMachine не должно быть инициализации ResourceBundle.
Вынеси из CashMachine сообщение о выходе в ConsoleHelper, назови метод printExitMessage.

3. Это всё! Красоту можешь наводить самостоятельно. Тестов на этот пункт не будет.
Например:
3.1. Исправить выводимые тексты.
3.2. Добавить ресурсы для нескольких локалей. Например, еще и для русской.
3.3. Добавить валидацию вводимых номиналов.

Твои достижения:
1. разобрался с паттерном Command.
2. подружился с Жадным алгоритмом.
3. познакомился с локализацией.
4. стал больше знать и уметь.
5. увидел, как раскладывать задачу на подзадачи.
6. продвинулся на шаг ближе к работе джава программистом.
7. решил одно из тестовых заданий, которое дают на собеседовании. Только тсссс, никому об этом не говори :).
Если когда-то тебе дадут такое задание, то не копируй это решение, а сделай свое по аналогии.

Поздравляю!


Требования:
1.	Класс CashMachine должен содержать public static final поле RESOURCE_PATH типа String.
2.	Класс ConsoleHelper должен содержать public static void метод printExitMessage().
3.	Поздравляю, это все на этот уровень!


CashMachine (15)

1. В LoginCommand, WithdrawCommand добавь поле private ResourceBundle res, которое инициализируй соответствующим ресурсом.
Для LoginCommand ресурс login_en.properties.
Для WithdrawCommand ресурс withdraw_en.properties.

2. Для ресурса common_en.properties замени все строки в ConsoleHelper.
Для этого создай приватное статическое поле ResourceBundle res в классе ConsoleHelper и инициализируй соответствующим ресурсом.
Важно: путь к ресурсам строй динамически, для этого используй у класса CashMachine метод getPackage()




CashMachine (15)

Ты уже разобрался с ResourceBundle - это круто.
Теперь мы сможем прикрутить локализацию, т.е. поддержку нескольких языков.

1. В DepositCommand, ExitCommand, InfoCommand добавь поле private ResourceBundle res, которое инициализируй соответствующим ресурсом.
Для DepositCommand ресурс deposit_en.properties.
Для ExitCommand ресурс exit_en.properties.
Для InfoCommand ресурс info_en.properties.
Важно: путь к ресурсам строй динамически, для этого используй у класса CashMachine метод getPackage()
2. Для каждого нового ресурса замени все строки в соответствующей команде.




CashMachine (15)

В задании 11 мы захардкодили номер кредитной карточки с пином, с которыми разрешим работать нашему банкомату.
Но юзеров может быть много. Не будем же мы их всех хардкодить! Если понадобится добавить еще одного пользователя,
то придется передеплоить наше приложение. Есть решение этой проблемы.

Смотри, добавился новый пакет resources, в котором мы будем хранить наши ресурсные файлы.
В этом пакете есть файл verifiedCards.properties, в котором заданы карточки с пинами.

1. В LoginCommand добавь поле private ResourceBundle validCreditCards.
При объявлении инициализируй это поле данными из файла verifiedCards.properties.
Почитай в инете, как это делается для ResourceBundle.
Важно: путь к ресурсу verifiedCards.properties строй динамически, для этого используй у класса CashMachine метод getPackage()
2. Замени хардкоженные данные кредитной карточки и пина на проверку наличия данных в ресурсе verifiedCards.properties.




CashMachine (15)

Поздравляю, ты реализовал WithdrawCommand! Основной функционал завершен. Дальше можно допиливать и наводить красоту.
Реализуем одну плюшку. Можно и без нее, но с ней - красивее.
Это верификация кредитной карты пользователя. Нет, никакого API сторонних либ не будет. Только консольная обработка.

Итак, назовем эту операцию LOGIN и сделаем для нее команду.
1. Добавь в операции LOGIN с ординал = 0
2. Запрети пользователю выбирать эту операцию из списка.
В единственном методе для поиска операций запрети доступ по ординал - бросим IllegalArgumentException.
3. Создай LoginCommand по аналогии с другими командами, в котором захардкодь номер карточки с пином
123456789012 и 1234 соответственно.
4. Реализуй следующую логику для команды LoginCommand:
4.1. Пока пользователь не введет валидные номер карты и пин - выполнять следующие действия:
4.2. Запросить у пользователя 2 числа - номер кредитной карты, состоящий из 12 цифр, и пин - состоящий из 4 цифр.
4.3. Вывести юзеру сообщение о невалидных данных, если они такими являются.
4.4. Если данные валидны, то проверить их на соответствие захардкоженным (123456789012 и 1234).
4.5. Если данные в п. 4.4. идентифицированы, то сообщить, что верификация прошла успешно.
4.6. Если данные в п. 4.4. НЕ идентифицированы, то вернуться к п.4.1.
5. Исправь CommandExecutor. Добавь в allKnownCommandsMap новую операцию.
6. Исправь метод main.
Операция LOGIN должна запускаться один раз, до выполнения всех операций.
Не забудь о InterruptOperationException, в любом месте пользователь может завершить работу с банкоматом. Поэтому добавь вызов операции внутрь блока try-catch.




CashMachine (15)

Сегодня мы займемся командой WithdrawCommand - это самая сложная операция.

1. Реализуй следующий алгоритм для команды WithdrawCommand:
1.1. Считать код валюты (метод уже есть).
1.2. Получить манипулятор для этой валюты.
1.3. Пока пользователь не введет корректные данные выполнять:
1.3.1. Попросить ввести сумму.
1.3.2. Если введены некорректные данные, то сообщить об этом пользователю и вернуться к п. 1.3.
1.3.3. Проверить, достаточно ли средств на счету.
Для этого в манипуляторе создай метод boolean isAmountAvailable(int expectedAmount), который вернет true, если денег достаточно для выдачи.
Если недостаточно, то вернуться к п. 1.3.
1.3.4. Списать деньги со счета. Для этого в манируляторе создай метод
Map&lt;Integer, Integer&gt; withdrawAmount(int expectedAmount), который вернет карту HashMap&lt;номинал, количество&gt;.
Подробно логику этого метода смотри в п.2.
1.3.5. Вывести пользователю результат из п. 1.3.4. в следующем виде:
&lt;табуляция&gt;номинал - количество.
Сортировка - от большего номинала к меньшему.
Вывести сообщение об успешной транзакции.
1.3.6. Перехватить исключение NotEnoughMoneyException, уведомить юзера о нехватке банкнот и вернуться к п. 1.3.

2. Логика основного метода withdrawAmount:
2.1. Внимание!!! Метод withdrawAmount должен возвращать минимальное количество банкнот, которыми набирается запрашиваемая сумма.
Используй Жадный алгоритм (use google).
Если есть несколько вариантов, то использовать тот, в котором максимальное количество банкнот высшего номинала.
Если для суммы 600 результат - три банкноты: 500 + 50 + 50 = 200 + 200 + 200, то выдать первый вариант.
Пример, надо выдать 600.
В манипуляторе есть следующие банкноты:
500 - 2
200 - 3
100 - 1
50 - 12
Результат должен быть таким:
500 - 1
100 - 1
т.е. всего две банкноты (это минимальное количество банкнот) номиналом 500 и 100.

2.2. Мы же не можем одни и те же банкноты выдавать несколько раз, поэтому
если мы нашли вариант выдачи денег (п.2.1. успешен), то вычесть все эти банкноты из карты в манипуляторе.

2.3. метод withdrawAmount должен кидать NotEnoughMoneyException, если купюрами невозможно выдать запрашиваемую сумму.
Пример, надо выдать 600.
В манипуляторе есть следующие банкноты:
500 - 2
200 - 2
Результат - данными банкнотами невозможно выдать запрашиваемую сумму. Кинуть исключение.
Не забудь, что в некоторых случаях картой кидается ConcurrentModificationException.


CashMachine (9)

Сегодня мы займемся командой ExitCommand.
1. Реализуй следующую логику в команде ExitCommand:
   1.1. Спросить, действительно ли пользователь хочет выйти - варианты &lt;y,n&gt;.
   1.2. Если пользователь подтвердит свои намерения, то попрощаться с ним.
   1.3. Если пользователь не подтвердит свои намерения, то не прощаться с ним, а просто выйти.

Это всё хорошо, но бывают случаи, когда срочно нужно прервать операцию, например, если пользователь ошибся с выбором операции.
Для этого у нас есть InterruptOperationException.
2.Реализуй следующую логику:
2.1. Если пользователь в любом месте ввел текст &#39;EXIT&#39; любым регистром, то выбросить InterruptOperationException.
2.2. Найди единственное место, куда нужно вставить эту логику. Реализуй функционал в этом единственном методе.

3. Заверни тело метода main в try-catch и обработай исключение InterruptOperationException.
   Попрощайся с пользователем в блоке catch используя ConsoleHelper.





CashMachine (8)

Пора привести в порядок наш main, уж очень там всего много, чего не должно быть.

1. Перенеси логику из main в DepositCommand и InfoCommand.
   Проверим, что там стало с main? Цикл, в котором спрашиваем операцию у пользователя, а потом вызываем метод у CommandExecutor.
   И так до бесконечности... надо бы придумать условие выхода из цикла.
   Исправь цикл, чтоб он стал do-while. Условие выхода - операция EXIT.

2. Давай запустим прогу и пополним счет на EUR 100 2 и USD 20 6, и посмотрим на INFO.
   Ничего не понятно, т.к. создались 2 манипулятора: первый для EUR, второй для USD.
   Давай улучшим логику InfoCommand. Надо вывести баланс по каждому манипулятору.

2.1. В классе CurrencyManipulatorFactory создай статический метод getAllCurrencyManipulators(), который вернет Collection всех манипуляторов.
У тебя все манипуляторы хранятся в карте, не так ли? Если нет, то отрефактори.
2.2. В InfoCommand в цикле выведи [код валюты - общая сумма денег для выбранной валюты].
Запустим прогу и пополним счет на EUR 100 2 и USD 20 6, и посмотрим на INFO.
Все работает правильно?
EUR - 200
USD - 120
Отлично!

3. Запустим прогу и сразу первой операцией попросим INFO. Ничего не вывело? Непорядок.
   Добавь в манипулятор метод boolean hasMoney(), который будет показывать, добавлены ли какие-то банкноты или нет.

4. В InfoCommand используй метод п.3. и выведи фразу &quot;No money available.&quot;, если нет денег в банкомате.


CashMachine (7)

Возвращаемся к паттерну Command.

1. Создай пакет command, в нем будут все классы, относящиеся к этой логике.
   Подумай над модификатором доступа для всех классов в этом пакете.

2. Создай интерфейс Command с методом void execute().

3. Для каждой операции создай класс-команду, удовлетворяющую паттерну Command.
   Имена классов DepositCommand, InfoCommand, WithdrawCommand, ExitCommand.

4. Создай public класс CommandExecutor, через который можно будет взаимодействовать со всеми командами.
   Создай ему статическую карту Map&lt;Operation, Command&gt; allKnownCommandsMap, которую проинициализируй всеми известными нам операциями и командами.

4.1 Создай метод public static final void execute(Operation operation), который будет дергать метод execute у нужной команды.
Реализуй эту логику.
4.2. Расставь правильно модификаторы доступа учитывая, что единственная точка входа - это метод execute.

Проверяем, чтоб структура соответствовала тестам на сервере.
Логику будем переносить в следующем таске.




CashMachine (6)

Чтобы отрефакторить код в соответствии с паттерном Command, нужно выделить в коде несколько логических блоков кода.
У нас пока два таких блока: 1) код операции DEPOSIT, 2) код операции INFO.
Они захардкожены в методе main. Нужно от этого избавиться.
Нужно сделать так, чтобы пользователь сам выбирал, какую операцию на данный момент нужно выполнять.

1. В энум Operation добавь статический метод Operation getAllowableOperationByOrdinal(Integer i)
   Должен возвращать элемент энума: для 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT;
   На некорректные данные бросать IllegalArgumentException.

2. В классе ConsoleHelper реализуй логику статического метода Operation askOperation().
   Спросить у пользователя операцию.
   Если пользователь вводит 1, то выбирается команда INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT;
   Используй метод, описанный в п.1.
   Обработай исключение - запроси данные об операции повторно.


Требования:
1.	Энум Operation должен иметь статический метод Operation getAllowableOperationByOrdinal(Integer i).
2.	Класс ConsoleHelper должен иметь метод Operation askOperation().



CashMachine (5)

1.В предыдущем таске мы реализовали основную логику операции DEPOSIT.
Но посмотреть результат так и не удалось.
Поэтому создай в манипуляторе метод int getTotalAmount(), который посчитает общую сумму денег для выбранной валюты.

2. Добавь вызов метода getTotalAmount() в метод main.
   Всё работает верно? Тогда движемся дальше.
   Видно, что метод getTotalAmount() считает то, что нам необходимо для операции INFO.
   Поэтому пришло время небольшого рефакторинга.
   !!Читайте паттерн Command.
   Однако, перед рефакторингом нужно еще разобраться в одном вопросе. Но об этом не сейчас.



CashMachine (4)

1. Выберем операцию, с которой мы сможем начать.
   Подумаем. В банкомате еще денег нет, поэтому INFO и WITHDRAW протестить не получится.
   Начнем с операции DEPOSIT - поместить деньги.
   Считаем с консоли код валюты, потом считаем номинал и количество банкнот, а потом добавим их в манипулятор.

2. Чтобы считать код валюты, добавим статический метод String askCurrencyCode() в ConsoleHelper.
   Этот метод должен предлагать пользователю ввести код валюты, проверять, что код содержит 3 символа.
   Если данные некорректны, то сообщить об этом пользователю и повторить.
   Если данные валидны, то перевести код в верхний регистр и вернуть.

3. Чтобы считать номинал и количество банкнот, добавим статический метод String[] getValidTwoDigits(String currencyCode) в ConsoleHelper.
   Этот метод должен предлагать пользователю ввести два целых положительных числа.
   Первое число - номинал, второе - количество банкнот.
   Никаких валидаторов на номинал нет. Т.е. 1200 - это нормальный номинал.
   Если данные некорректны, то сообщить об этом пользователю и повторить.
   Пример вводимых данных:
   200 5

4. В классе CurrencyManipulator создай метод void addAmount(int denomination, int count),
   который добавит введенные номинал и количество банкнот.

5. Пора уже увидеть приложение в действии.
   В методе main захардкодь логику пункта 1.
   Кстати, чтобы не было проблем с тестами на стороне сервера, добавь в метод main первой строчкой Locale.setDefault(Locale.ENGLISH);
   Запускаем, дебажим, смотрим.



CashMachine (3)

1. Создай класс CurrencyManipulator, который будет хранить всю информацию про выбранную валюту.
   Класс должен содержать:
   1.1 String currencyCode - код валюты, например, USD. Состоит из трех букв.
   1.2 Map&lt;Integer, Integer&gt; denominations - это Map&lt;номинал, количество&gt;.
   Чтобы можно было посмотреть, к какой валюте относится манипулятор, добавим геттер для currencyCode.
   Очевидно, что манипулятор никак не может функционировать без названия валюты,
   поэтому добавим конструктор с этим параметром и проинициализируем currencyCode.

2. Валют может быть несколько, поэтому нам понадобится фабрика, которая будет создавать и хранить манипуляторы.
   Создай класс CurrencyManipulatorFactory со статическим методом getManipulatorByCurrencyCode(String currencyCode).
   В этом методе будем создавать нужный манипулятор, если он еще не существует, либо возвращать ранее созданный.
   Регистр при поиске манипулятора валюты не должен учитываться.
   Подумай, где лучше хранить все манипуляторы?  Маленькая подсказка, поле должно называться map.

Сделайте так, чтобы невозможно было создавать объекты CurrencyManipulatorFactory класса.



CashMachine (2)

1. Создай в ConsoleHelper два статических метода:
   1.1 writeMessage(String message), который будет писать в консоль наше сообщение.
   1.2 String readString(), который будет считывать с консоли строку и возвращать ее.
   Если возникнет какое-то исключение при работе с консолью, то перехватим его и не будем обрабатывать.
   Кстати, создадим только один экземпляр BufferedReader-а, в статическом поле bis.

2. Создай пакет exception, в который поместим два checked исключения:
   2.1 InterruptOperationException будем кидать, когда нужно прервать текущую операцию и выйти из приложения.
   2.2 NotEnoughMoneyException будем кидать, когда не сможем выдать запрашиваемую сумму.



CashMachine (1)

Давай напишем эмулятор работы банкомата.
Операции, которые будем поддерживать, следующие:
поместить деньги, снять деньги, показать состояние банкомата.
Также будем поддерживать мультивалютность.
Купюрами будем оперировать теми, которые поместим в банкомат.
Если для снятия требуемой суммы будет недостаточно банкнот, то сообщим юзеру об этом.
Понятно, что всё должно быть user friendly, поэтому придется наводить рюшечки.

Итак:
1. Создай класс CashMachine с методом main.
2. Наши операции зададим в энуме Operation: INFO, DEPOSIT, WITHDRAW.
   Т.к. всё должно быть user friendly, то на выход из приложения надо попрощаться с юзером.
   Поэтому добавим еще операцию EXIT.
3. Т.к мы будем активно общаться с юзером, то будет много выводимого текста.
   Чтобы работу с консолью сосредоточить в одном месте, создадим класс ConsoleHelper.

