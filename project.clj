(defproject prime-stepper "0.1.0"
  :description "Step in time.  Er, I mean step through primes"
  :url "http://github.com/afazio/prime-stepper"
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot prime-stepper.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
