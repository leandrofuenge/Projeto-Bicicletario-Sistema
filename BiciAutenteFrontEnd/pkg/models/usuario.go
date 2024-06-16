package models

// Usuario representa um usuário do sistema
type Usuario struct {
	CPF   string `json:"cpf"`
	Nome  string `json:"nome"`
	Email string `json:"email"`
}
