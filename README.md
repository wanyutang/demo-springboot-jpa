# demo-jpa-test

Spring Boot 架構下開發JPA程式開發規範測試

- 轉載自 [JPA程式開發規範-Spring Boot.docx](https://docs.google.com/document/d/1lc1eJQxYJ8KdfDTcueoIJ4J2F9cKSMWr/edit?usp=sharing&ouid=100460925661581070808&rtpof=true&sd=true)
- 範例參考 <https://github.com/wanyutang/demo-jpa-test>
## 1. OneToMany 關聯

在專案中的所有相關實體關聯中，當相關的資料庫表格有設定主鍵（PK），則預設使用`@OneToMany(mappedBy)`和`@ManyToOne`標註來指定關聯。
這樣可以建立適當的關聯映射，並確保資料的一致性。

所有關聯 Tag 中統一使用`fetch = FetchType.LAZY`屬性，以確保關聯的延遲加載，以提高效能和減少不必要的資料擷取。

## 2. 自定義查詢條件 Specification

動態創建查詢條件，根據需求組合不同的條件返回 Specification 並使用 CriteriaBuilder 建立查詢條件。

## 3. 使用 Specification 進行根據關聯表 join 條件的查詢

使用 Specification and root.join() and  CriteriaBuilder 物件根據關聯表的欄位進行查詢。


## 4. 更新資料時應先查詢再進行更新

在進行資料更新時，應先進行查詢操作以獲取要更新的物件。接著，修改該物件的屬性後進行更新，避免直接創建新物件導致其他欄位被更新成空值。

請確保先進行查詢操作並獲取到要更新的物件，再進行修改和保存操作，以確保其他欄位的值不被意外更新成空值

## 5. 更新資料時避免更新大型物件欄位

如果要更新的資料表包含大型物件欄位（如TEXT、BLOB等），但僅需要更新純文字欄位，可以採用以下方法：

1. 確定需要更新的純文字欄位。
2. 使用EntityManager的createNativeQuery()方法創建自訂的SQL UPDATE語句，僅包含要更新的純文字欄位。

這樣的做法可以避免更新不必要的大型物件欄位，僅專注於更新純文字欄位，從而提升效能。

## 6. 在關聯實體的新增或刪除時的操作

維護關聯關係，確保資料的一致性。

## 7. 使用 Query Method 進行查詢

使用 Query Method 查詢，可以在 Repository 中定義類似的方法根據需求進行查詢。

透過遵循命名規則，根據不同的需求定義類似的方法 Spring Data JPA 將根據方法名稱自動生成相對應的查詢，進行資料查詢操作。

## 8. NativeQuery 回傳自定義封裝物件

在專案中，非必要情況下請避免使用NativeQuery，除非以下所有條件都滿足：

1. 使用其他JPA寫法無法達到需求。
2. 經開發團隊討論並經過確認，確實需要使用NativeQuery。
3. 有效能問題需要透過NativeQuery來優化。

使用NativeQuery可能會增加程式碼的複雜度，並且使程式與特定的資料庫平台綁定。請在必要時謹慎使用。