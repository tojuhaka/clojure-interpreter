(ns interpreter.core-test
  (:require [clojure.test :refer :all]
            [interpreter.core :refer :all]))
(use '[clojure.string :only (split)])

(deftest test-tokenizer
  (is (= [{:type CHR :value "def"},
          {:type BLANK :value " "},
          {:type INT :value "1"}] (tokenize (split "def 1" #"")))))

