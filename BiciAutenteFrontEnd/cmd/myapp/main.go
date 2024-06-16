package main

import (
	"BiciAutenteFrontEnd/internal/api"
	_ "BiciAutenteFrontEnd/pkg/models"
	_ "fmt"
	"log"
	"os"

	"github.com/gofiber/fiber/v2"
	"github.com/joho/godotenv"
)

func main() {
	// Carregar variáveis de ambiente de um arquivo .env
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Erro ao carregar arquivo .env")
	}

	// URL base do backend Java
	baseURL := os.Getenv("BASE_URL")

	// Inicializar a aplicação Fiber
	app := fiber.New()

	// Rota de login
	app.Post("/login", func(c *fiber.Ctx) error {
		type LoginRequest struct {
			CPF   string `json:"cpf"`
			Senha string `json:"senha"`
		}

		var loginReq LoginRequest
		if err := c.BodyParser(&loginReq); err != nil {
			return c.Status(fiber.StatusBadRequest).JSON(fiber.Map{
				"error": "CPF e senha são obrigatórios",
			})
		}

		usuario, err := api.FazerLogin(baseURL+"/api/login", loginReq.CPF, loginReq.Senha)
		if err != nil {
			return c.Status(fiber.StatusUnauthorized).JSON(fiber.Map{
				"error": "CPF ou senha inválidos",
			})
		}

		return c.JSON(usuario)
	})

	// Iniciar o servidor Fiber
	log.Fatal(app.Listen(":3000"))
}
