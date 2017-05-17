(ns interpreter.core
  (:gen-class))

(use '[clojure.string :only (split blank?)])

(def INT "INT")
(def CHR "CHR")
(def PLUS "PLUS")
(def BLANK "BLANK")

(defn digit? [str]
  (re-matches #"^\d+$" str))

(defn plus? [str]
  (re-matches #"\+" str))

(defn char-type [s]
  (cond
    (digit? s) {:type INT, :value s}
    (plus? s) {:type PLUS, :value s}
    (blank? s) {:type BLANK :value s}
    :default {:type CHR :value s}))

(defn unify [a x]
  (let [prev-type (get (last a) :type)
        cur-type (get x :type)
        prev-value (get (last a) :value)
        cur-value (get x :value)]
    (cond
      (identical? prev-type cur-type) (concat (drop-last a)
        [{:type cur-type, :value (str prev-value cur-value)}])
       :else (concat a [x]))))

(defn tokenize [lst]
  (let [char-types (map (fn [x] (char-type x)) lst)]
    (reduce unify [] char-types)))

(defn -main
  "Interpreter example to learn some clojure."
  [& args]

  (let [text (read-line)]
        (println (tokenize (split text #"")))))




