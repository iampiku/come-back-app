import { Button } from "../../components/ui/Button";

import { describe, expect, it, vi } from "vitest";
import { render, screen } from "@testing-library/react";

describe("Button", () => {
	it("should renders with default props", () => {
		render(<Button>Click me</Button>);

		const button = screen.getByRole("button", { name: /click me/i });

		expect(button).toBeDefined();
		expect(button).toHaveClass("btn-md");
		expect(button).toHaveClass("btn-primary");
	});

	it("should render spinner when loading is true", () => {
		render(<Button loading>Click me</Button>);
		const spinner = screen
			.getByText(/click me/i)
			.querySelector(".loading-spinner");

		expect(spinner).toBeDefined();
	});

	it("should not allow click when loading is true", () => {
		const handleClick = vi.fn();
		render(
			<Button loading onClick={handleClick}>
				Click me
			</Button>,
		);

		const button = screen.getByRole("button");
		button.click();

		expect(handleClick).toBeCalledTimes(0);
	});

	it("should be disabled when loading is true", () => {
		render(<Button loading>Click me</Button>);
		const button = screen.getByRole("button", { name: /click me/i });

		expect(button).toBeDisabled();
	});

	it("should and call onClick handler when clicked", () => {
		const handleClick = vi.fn();

		render(<Button onClick={handleClick}>Click me</Button>);

		const button = screen.getByRole("button", { name: /click me/i });
		button.click();

		expect(handleClick).toBeCalled();
	});

	it("should be disabled when disabled prop is true", () => {
		render(<Button disabled>Click me</Button>);
		const button = screen.getByRole("button", { name: /click me/i });

		expect(button).toBeDisabled();
	});
});
