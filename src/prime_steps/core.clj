(ns prime-steps.core
  (:gen-class))

(defn divisible? "Is m divisible by n?"
  [m n]
  (= (mod m n) 0))

(defn quick-prime-check "Check if n is prime by checking divisibility by numbers up to sqrt(n)
  starting at 5, ignoring mod 2 and mod 3."
  [n]
  (let [m (Math/sqrt n)]
    (not-any? #(or (divisible? n %)
                   (divisible? n (+ % 2)))
              (range 5 m 6))))

(defn prime? "Is n prime?"
  [n]
  (and (number? n)
       (divisible? n 1)
       (> n 1)
       (if (divisible? n 2) (= n 2)
           (if (divisible? n 3) (= n 3)
               (quick-prime-check n)))))

(defn steps-to-next-prime "How many steps until we reach the next prime number from n"
  ([n] (steps-to-next-prime n 1))
  ([n curr-steps]
   (if (prime? (+ n curr-steps))
     curr-steps
     (recur n (inc curr-steps)))))

(defn prime-steps "lazy seq of steps for reaching primes starting at 1"
  ([] (prime-steps 1))
  ([n]
   (let [step (steps-to-next-prime n)]
     (cons step
           (lazy-seq (prime-steps (+ n step)))))))

(defn generate-steps "generate n steps for reaching primes starting at 1"
  ([n] (generate-steps n 1))
  ([n start] (generate-steps n start []))
  ([n start steps]
   (if (= n 0)
     steps
     (let [step (steps-to-next-prime start)
           next-start (+ start step)]
       (recur (dec n) next-start (conj steps step))))))
       

(defn -main "Given n, print out n steps to primes starting at 1 or second argument if it exists."
  [& args]
  (let [n (Integer. (first args))
        start (Integer. (nth args 1 1))]
    (println (clojure.string/join "," (take n (prime-steps start))))))
