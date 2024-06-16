package main

import (
	"github.com/gofiber/fiber/v2"
)

func main() {
	// Criar uma instância do Fiber
	app := fiber.New()

	// Middleware de CORS
	app.Use(func(c *fiber.Ctx) error {
		c.Set("Access-Control-Allow-Origin", "*") // Permitir todas as origens (não seguro para produção)
		c.Set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
		c.Set("Access-Control-Allow-Headers", "Content-Type, Authorization")
		if c.Method() == "OPTIONS" {
			return c.SendStatus(fiber.StatusOK)
		}
		return c.Next()
	})

	// Rota de login
	app.Post("/login", func(c *fiber.Ctx) error {
		// Lógica de autenticação aqui
		return c.JSON(fiber.Map{"mensagem": "Login realizado com sucesso"})
	})

	// Iniciar o servidor na porta 3000
	err := app.Listen(":3000")
	if err != nil {
		return
	}
}
