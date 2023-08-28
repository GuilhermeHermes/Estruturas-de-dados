#include <iostream>

class BinaryTree {
private:
    int value;
    BinaryTree* left_tree;
    BinaryTree* right_tree;
    BinaryTree* father;
    int level;

public:
    BinaryTree(int value) {
        this->value = value;
        this->left_tree = nullptr;
        this->right_tree = nullptr;
        this->father = nullptr;
        this->level = 1;
    }

    void insertion(int value) {
        if (value < this->value) {
            if (this->left_tree == nullptr) {
                this->left_tree = new BinaryTree(value);
                this->left_tree->father = this;
                this->left_tree->level = this->level + 1;
            } else {
                this->left_tree->insertion(value);
            }
        } else if (value > this->value) {
            if (this->right_tree == nullptr) {
                this->right_tree = new BinaryTree(value);
                this->right_tree->father = this;
                this->right_tree->level = this->level + 1;
            } else {
                this->right_tree->insertion(value);
            }
        }
    }

    bool search(int value) {
        if (this->value == value) {
            std::cout << "Valor encontrado!" << std::endl;
            return true;
        }
        if (value < this->value && this->left_tree != nullptr) {
            return this->left_tree->search(value);
        }
        if (value > this->value && this->right_tree != nullptr) {
            return this->right_tree->search(value);
        }
        return false;
    }

    BinaryTree* remove(int value) {
        if (this == nullptr) {
            return nullptr;
        }

        if (value < this->value) {
            if (this->left_tree != nullptr) {
                this->left_tree = this->left_tree->remove(value);
            }
        } else if (value > this->value) {
            if (this->right_tree != nullptr) {
                this->right_tree = this->right_tree->remove(value);
            }
        } else {
            if (this->right_tree == nullptr && this->left_tree == nullptr) {
                delete this;
                return nullptr;
            } else if (this->left_tree == nullptr) {
                BinaryTree* temp = this->right_tree;
                delete this;
                return temp;
            } else if (this->right_tree == nullptr) {
                BinaryTree* temp = this->left_tree;
                delete this;
                return temp;
            } else {
                BinaryTree* min_node = this->right_tree->find_min();

                this->value = min_node->value;

                this->right_tree = this->right_tree->remove(min_node->value);
            }
        }

        return this;
    }

    BinaryTree* find_min() {
        BinaryTree* current_node = this;
        while (current_node->left_tree != nullptr) {
            current_node = current_node->left_tree;
        }
        return current_node;
    }

    void print_tree() {
        if (this == nullptr) {
            return;
        }

        if (this->left_tree != nullptr) {
            this->left_tree->print_tree();
        }

        std::cout << this->value << " ";

        if (this->right_tree != nullptr) {
            this->right_tree->print_tree();
        }
    }
};

int main() {
    BinaryTree* abb = new BinaryTree(10);
    abb->insertion(8);
    abb->insertion(15);
    abb->insertion(11);

    std::cout << "ARVORE 1" << std::endl;
    abb->print_tree();
    std::cout << std::endl;

    abb->remove(11);

    std::cout << "ARVORE 2" << std::endl;
    abb->print_tree();
    std::cout << std::endl;

    if (abb->search(11)) {
        std::cout << "Valor encontrado!" << std::endl;
    } else {
        std::cout << "Valor não encontrado!" << std::endl;
    }

    delete abb;
    return 0;
}
