



(defn get-bytes [string]
    (if (not (nil? string)) (map byte string)))
(defn get-binary-string [string] 
    (if (not (nil? string)) 
        (clojure.string/join 
        (map #(if (< (count %) 8 ) (str "0" %))
           (map #(Integer/toBinaryString %)
                 (get-bytes string))))))
 
(defn sextets [binstr] 
    (if (not (nil? binstr))  
    (partition 6 6 [\0] binstr)))

(defn pad [sextets] 
    (if (= (mod (count sextets)  3) 0) (conj (vec sextets) '(\0 \0 \0 \0 \0 \0))))

(defn get-ascii [int] 
    (cond (< int 25)    (+ int 65)
     (> int 25)    (+ int 71)))

 (map #(char (get-ascii (Integer/parseInt % 2))) 
    (map clojure.string/join (sextets (reduce str (map clojure.string/join (pad (sextets (get-binary-string "Ma"))))))))





