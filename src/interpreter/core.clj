(ns interpreter.core
  (:gen-class))

(use '[clojure.string :only (split triml blank?)])

(def INT "INT")
(def CHR "CHR")
(def PLUS "PLUS")
(def BLANK "BLANK")

(defn digit? [str]
  (re-matches #"^\d+$" str))

(defn plus? [str]
  (re-matches #"\+" str))

(defn get-char-type [s] 
  (cond
    (digit? s) {:type INT, :value s}
    (plus? s) {:type PLUS, :value s}
    (blank? s) {:type BLANK :value s}
    :default {:type CHR :value s}))


(defn get-tokens [char_list]
  (let [tokens []])
  (map (fn [x] (get-char-type x)) char_list))

(defn -main
  "Interpreter example to learn some clojure"
  [& args]

  (let [text (read-line)]
    (println (get-tokens (split text #"")))))

