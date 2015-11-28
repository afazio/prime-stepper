(ns prime-stepper.core
  (:gen-class))

;; readability ftw
(defn divisible? "Is m divisible by n?"
  [m n]
  (= (mod m n) 0))

(defn prime?
  "Is n prime?  Uses memoized values of previously found primes to speed things
  up."
  [n prime-list]
  (and (number? n)
       (divisible? n 1)
       (> n 1)
       (let [m (Math/sqrt n)]
         (not-any? #(divisible? n %)
                   (take-while #(<= % m) prime-list)))))

(defn steps-to-next-prime
  "How many steps until we reach the next prime number from n."
  ([n prime-list] (steps-to-next-prime n prime-list 1))
  ([n prime-list curr-steps]
   (if (prime? (+ n curr-steps) prime-list)
     curr-steps
     (recur n prime-list (inc curr-steps)))))

(defn prime-steps
  "generate lazy seq of steps for reaching primes starting at n (default 1)."
  ([] (prime-steps 1))
  ([n] (prime-steps n []))
  ([n prime-list]
   (let [step (steps-to-next-prime n prime-list)
         next-prime (+ n step)]
     (cons step
           (lazy-seq (prime-steps next-prime (conj prime-list next-prime)))))))

(defn -main
  "Given n, print out n steps to primes starting at 1 or second argument if it exists."
  [& args]
  (let [n (Integer. (first args))
        start (Integer. (nth args 1 1))]
    (println (clojure.string/join "," (take n (prime-steps start))))))
