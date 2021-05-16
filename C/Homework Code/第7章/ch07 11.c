//第七章 第11题
#include <stdio.h>
#include <stdbool.h>

#define ARTICHOKE_PRICE_PER_LB 2.05
#define BEET_PRICE_PER_LB 1.15
#define CARROT_PRICE_PER_LB 1.09

#define SHIPPING_5LB 6.50
#define SHIPPING_20LB 14.00
#define SHIPPING_OVER_20LB_RATE 0.5

#define DISCOUNT_RATE 0.05

void flush_input_buffer(void);
float calculate_shipping(float weight);

int main(void)
{
	float artichoke_weight = 0, beet_weight = 0, carrot_weight = 0, total_weight;
	float artichoke_price, beet_price, carrot_price, subtotal, discount, shipping, total;
	bool discount_flag;
	float weight;
	char option;

	while (1)
	{
		printf("你想要点什么?\n");
		printf("a) 洋蓟  b) 甜菜  c) 胡萝卜  q) 退出\n");
		option = getchar();
		switch (option)
		{
		case ('q'):
			printf("Bye.\n");
			return 0; // exit program

		case ('a'): // artichokes
			printf("你想要多少磅的洋蓟? ");
			if (scanf("%f", &weight) == 1)
				artichoke_weight += weight;
			else
			{
				flush_input_buffer();
				printf("无效输入，请重试.\n");
				continue; // repeat main program loop
			}
			break;

		case ('b'): // beets
			printf("你想要多少磅的甜菜? ");
			if (scanf("%f", &weight) == 1)
				beet_weight += weight;
			else
			{
				flush_input_buffer();
				printf("无效输入，请重试\n");
				continue; // repeat main program loop
			}
			break;

		case ('c'): // carrots
			printf("你想要多少磅的胡萝卜? ");
			if (scanf("%f", &weight) == 1)
				carrot_weight += weight;
			else
			{
				flush_input_buffer();
				printf("无效输入，请重试.\n");
				continue; // repeat main program loop
			}
			break;

		default:
			printf("无效输入，请重试.\n");
			continue; // repeat main program loop
		}

		// calculate subtotal
		artichoke_price = artichoke_weight * ARTICHOKE_PRICE_PER_LB;
		beet_price = beet_weight * BEET_PRICE_PER_LB;
		carrot_price = carrot_weight * CARROT_PRICE_PER_LB;
		subtotal = artichoke_price + beet_price + carrot_price;

		// calculate discount
		if (subtotal >= 100)
		{
			discount_flag = true;
			discount = DISCOUNT_RATE * subtotal;
		}
		else
			discount_flag = false;

		// calculate shipping
		total_weight = artichoke_weight + beet_weight + carrot_weight;
		shipping = calculate_shipping(total_weight);

		// grand total
		total = subtotal + shipping - (discount_flag ? discount : 0.0);

		printf("\n");
		printf("你总共订了:\n\n");
		printf("洋蓟: %.2flbs @ $%.2f/lb: $%.2f\n",
			artichoke_weight, ARTICHOKE_PRICE_PER_LB, artichoke_price);
		printf("甜菜: %.2flbs @ $%.2f/lb: $%.2f\n",
			beet_weight, BEET_PRICE_PER_LB, beet_price);
		printf("胡萝卜: %.2flbs @ $%.2f/lb: $%.2f\n",
			carrot_weight, CARROT_PRICE_PER_LB, carrot_price);
		printf("\n");
		printf("总价: $%.2f\n", subtotal);
		if (discount_flag)
			printf("%.0f%% discount: $%.2f\n", DISCOUNT_RATE * 100, discount);
		printf("运输费用: $%.2f\n", shipping);
		printf("订单总价: $%.2f\n", total);
		printf("\n");

		flush_input_buffer();
	}
}

void flush_input_buffer(void)
{
	while (getchar() != '\n')
		;
}

float calculate_shipping(float weight)
{
	if (weight < 5.0)
		return SHIPPING_5LB;
	else if (weight < 20.0)
		return SHIPPING_20LB;
	else
		return SHIPPING_20LB + SHIPPING_OVER_20LB_RATE * (weight - 20.0);
}
