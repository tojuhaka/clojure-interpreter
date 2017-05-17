(ns interpreter.core-test
  (:require [clojure.test :refer :all]
            [interpreter.core :refer :all]))
(use '[clojure.string :only (split)])

(deftest test-tokenizer
  (is (= [{:type CHR :value "def"},
          {:type BLANK :value " "},
          {:type INT :value "12"},
          {:type CHR :value "test"},
          {:type BLANK :value "  "}
          ] (tokenize (split "def 12test  " #"")))))

