(ns testing
  (:require
    [invoice-item :as invoice-item]))

(use 'clojure.test)

(deftest basic_testing
  (testing "Calculating Subtotal with 100% Discount"
    (let [item {:invoice-item/precise-quantity 2 :invoice-item/precise-price 30.0 :invoice-item/discount-rate 100}]
      (is (= (invoice-item/subtotal item) 0.0))))

  (testing "Calculating Subtotal with Price 0"
    (let [item {:invoice-item/precise-quantity 2 :invoice-item/precise-price 0 :invoice-item/discount-rate 10}]
      (is (= (invoice-item/subtotal item) 0.0))))

  (testing "Calculating Subtotal without Discount"
    (let [item {:invoice-item/precise-quantity 2 :invoice-item/precise-price 5.0}]
      (is (= (invoice-item/subtotal item) 10.0))))

  (testing "Calculating Subtotal with Negative Quantity"
    (let [item {:invoice-item/precise-quantity -2 :invoice-item/precise-price 5.0 :invoice-item/discount-rate 10}]
      (is (= (invoice-item/subtotal item) -9.0))))

  (testing "Calculating Subtotal with 25% Discount"
    (let [item {:invoice-item/precise-quantity 3 :invoice-item/precise-price 10.0 :invoice-item/discount-rate 25}]
      (is (= (invoice-item/subtotal item) 22.5))))

  (testing "Calculating Subtotal with 50% Discount"
    (let [item {:invoice-item/precise-quantity 5 :invoice-item/precise-price 20.0 :invoice-item/discount-rate 50}]
      (is (= (invoice-item/subtotal item) 50.0)))))

(run-tests 'testing)
