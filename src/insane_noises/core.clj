(ns insane-noises.core)
(use 'overtone.live)

;; (defn -main (println "Hello overtone"))

(definst tri-wave
  [freq 440 attack 0.01 sustain 0.1 release 0.4 vol 0.4]
  (* (env-gen (lin attack sustain release) 1 1 0 1 FREE)
     (lf-tri freq)
     vol))
