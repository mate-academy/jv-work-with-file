# jv-work-with-file

Implement method `getStatistic(String fromFileName, String toFileName)` 
which will return some data from market after working day. 

This method has two parameters:
- `String fromFileName` - you should read data from this file
- `String toFileName` - you should write result to this file

Input file has `.csv` format. CSV is a simple file format used to store tabular data.
This type of files is very popular to storing information. So we will start working with it. 
CSV stands for "comma-separated values". Its data fields are most often separated, or delimited by a comma. 

For example, let's say you had a spreadsheet containing the following data:

| operation type | ammount  | 
| :------------: | :-------:|
| supply         | 30       | 
| buy            | 10       | 
| buy            | 13       | 
| supply         | 17       | 
| buy            | 10       | 

The above data could be represented in a CSV-formatted file as follows:
```csv
supply,30
buy,10
buy,13
supply,17
buy,10
```

__Your task is to read all data from input csv file, 
create a report and write it to newFile (the name of this file is the second parameter in the method).__

Example of the report:
```csv
supply,47
buy,33
result,14
```

Explanation:
- `supply = 30 + 17 = 47`
- `buy = 10 + 13 + 10 = 33`
- `result = supply - buy = 47 - 33 = 14`

#### [Try to avoid these common mistakes, while solving task](https://mate-academy.github.io/jv-program-common-mistakes/java-core/builder-file/work-with-file)
Реалізуйте метод `getStatistic(String fromFileName, String toFileName)`,
який повертатиме деякі дані з ринку після робочого дня. 
Цей метод має два параметри: - 
`String fromFileName` - ви повинні прочитати дані з цього файлу -
`String toFileName` - ви повинні записати результат у цей файл.
Вхідний файл має формат `.csv`. CSV – це простий формат файлу,
який використовується для зберігання табличних даних.
Цей тип файлів дуже популярний для зберігання інформації. 
Тож почнемо з ним працювати. CSV означає «значення, розділені комами». 
Його поля даних найчастіше відокремлюються або розмежовуються комами.
Наприклад, припустимо, у вас є електронна таблиця, що містить такі дані: 
Наведені вище дані можуть бути представлені у файлі у форматі CSV таким чином: 
_Your завдання полягає в тому, щоб прочитати всі дані з вхідного файлу csv,
створити звіт і записати його в newFile (ім’я цього файлу є другим параметром методу).__ 
Приклад звіту: ```csv supply,47 buy,33 результат,14 ``` Пояснення: 
