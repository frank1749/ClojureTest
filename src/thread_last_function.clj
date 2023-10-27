(ns thread_last_function)

(def invoice (clojure.edn/read-string (slurp "invoice.edn")))
;(println invoice)

(defn
  valid_item_data? [{taxes :taxable/taxes
                retentions :retentionable/retentions}]

  ;I implement a loop to get the valid values
  (let [valid_retention_rate?  (->> retentions (some #(= (:retention/rate %) 1)))
        valid_tax_rate? (->> taxes (some #(= (:tax/rate %) 19)))]
    (if (and valid_retention_rate? valid_tax_rate?)
      false
      (or valid_retention_rate? valid_tax_rate?))))

;I implement a filter comparing the invoice elements
(defn init
  [invoice]
  (->> (get invoice :invoice/items)
       (filter valid_item_data?)))
(doseq [item (init invoice)]
  ;I show the ids that have the condition
  (println "ID:" (:invoice-item/id item))
  )