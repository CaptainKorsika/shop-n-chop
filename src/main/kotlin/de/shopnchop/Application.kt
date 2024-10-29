package de.shopnchop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["de.shopnchop"])
class Application


fun main(args: Array<String>) {
    runApplication<Application>(*args)




}