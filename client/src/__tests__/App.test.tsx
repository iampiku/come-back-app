import { describe, it, expect } from "vitest";
import { render, screen, fireEvent } from "@testing-library/react";
import App from "../App";

describe("App", () => {
	it("renders and displays initial UI", () => {
		render(<App />);

		const heading = screen.getByText(/vite \+ react/i);
		expect(heading).toBeDefined();

		const img = screen.getByAltText(/react logo/i);
		expect(img).toBeDefined();

		const link = screen.getByRole("link");
		expect(link.getAttribute("href")).toBe("https://react.dev");

		const button = screen.getByRole("button", { name: /count is 0/i });
		expect(button).toBeDefined();

		fireEvent.click(button);
		expect(screen.getByRole("button", { name: /count is 1/i })).toBeDefined();
	});
});
