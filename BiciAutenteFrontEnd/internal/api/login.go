package api

import (
	"BiciAutenteFrontEnd/pkg/models"
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
)

// LoginResponse representa a resposta do login
type LoginResponse struct {
	Usuario models.Usuario `json:"usuario"`
}

// FazerLogin realiza a autenticação do usuário
func FazerLogin(url, cpf, senha string) (*models.Usuario, error) {
	// Criação do corpo da requisição
	requestBody, err := json.Marshal(map[string]string{
		"cpf":   cpf,
		"senha": senha,
	})
	if err != nil {
		return nil, fmt.Errorf("erro ao converter dados para JSON: %w", err)
	}

	// Criação da requisição HTTP
	resp, err := http.Post(url, "application/json", bytes.NewBuffer(requestBody))
	if err != nil {
		return nil, fmt.Errorf("erro ao fazer requisição: %w", err)
	}
	defer func(Body io.ReadCloser) {
		err := Body.Close()
		if err != nil {

		}
	}(resp.Body)

	// Verificação do status da resposta
	if resp.StatusCode != http.StatusOK {
		return nil, fmt.Errorf("erro na resposta do servidor: %s", resp.Status)
	}

	// Leitura e decodificação do corpo da resposta
	var loginResponse LoginResponse
	err = json.NewDecoder(resp.Body).Decode(&loginResponse)
	if err != nil {
		return nil, fmt.Errorf("erro ao decodificar resposta JSON: %w", err)
	}

	return &loginResponse.Usuario, nil
}
